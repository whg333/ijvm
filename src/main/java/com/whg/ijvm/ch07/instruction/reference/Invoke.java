package com.whg.ijvm.ch07.instruction.reference;

import com.whg.ijvm.ch07.classfile.uint.Uint16;
import com.whg.ijvm.ch07.heap.RClass;
import com.whg.ijvm.ch07.heap.RConstantPool;
import com.whg.ijvm.ch07.heap.RMethod;
import com.whg.ijvm.ch07.heap.RObject;
import com.whg.ijvm.ch07.heap.constant.InterfaceMethodRef;
import com.whg.ijvm.ch07.heap.constant.MethodLookup;
import com.whg.ijvm.ch07.heap.constant.MethodRef;
import com.whg.ijvm.ch07.instruction.Instruction;
import com.whg.ijvm.ch07.instruction.base.BytecodeReader;
import com.whg.ijvm.ch07.instruction.base.Index16Instruction;
import com.whg.ijvm.ch07.instruction.base.MethodInvokeLogic;
import com.whg.ijvm.ch07.runtime.OperandStack;
import com.whg.ijvm.ch07.runtime.RFrame;

public class Invoke {

    public static class INVOKE_STATIC extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            MethodRef methodRef = cp.getConstant(index.value());
            RMethod method = methodRef.resolveMethod();
            if(!method.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }
            MethodInvokeLogic.invokeMethod(frame, method);
        }
    }

    public static class INVOKE_SPECIAL extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RClass currentClass = frame.getMethod().getRClass();
            RConstantPool cp = currentClass.getRConstantPool();
            MethodRef methodRef = cp.getConstant(index.value());

            //弄清楚methodRef.resolveClass()和method.getRClass()的区别
            RClass clazz = methodRef.resolveClass();
            RMethod method = methodRef.resolveMethod();
            RClass methodClass = method.getRClass();

            if(method.getName().equals("<init>") && methodClass != clazz){
                throw new RuntimeException("NoSuchMethodError");
            }
            if(method.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }

            RObject ref = frame.getOperandStack().getRefFromTop(method.getArgSlotCount()-1);
            if(ref == null){
                throw new RuntimeException("NullPointerException");
            }

            //弄清楚refClass和currentClass以及methodClass的区别
            RClass refClass = ref.getRClass();
            if(method.isProtected()
                    && methodClass.isSuperClassOf(currentClass)
                    && methodClass.isSamePackage(currentClass)
                    && refClass != currentClass
                    && !refClass.isSubClassOf(currentClass)){
                throw new RuntimeException("IllegalAccessError");
            }

            RMethod methodToBeInvoked = method;
            if(currentClass.isSuper()
                && clazz.isSuperClassOf(currentClass)
                && !method.getName().equals("<init>")){

                methodToBeInvoked = MethodLookup.lookupMethodInClass(currentClass.getSuperClass(),
                        methodRef.getName(), methodRef.getDescriptor());
            }

            if(methodToBeInvoked == null || methodToBeInvoked.isAbstract()){
                throw new RuntimeException("AbstractMethodError");
            }

            MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
        }
    }

    public static class INVOKE_VIRTUAL extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RClass currentClass = frame.getMethod().getRClass();
            RConstantPool cp = currentClass.getRConstantPool();
            MethodRef methodRef = cp.getConstant(index.value());

            //弄清楚methodRef.resolveClass()和method.getRClass()的区别
            RClass clazz = methodRef.resolveClass();
            RMethod method = methodRef.resolveMethod();
            RClass methodClass = method.getRClass();

            if(method.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }

            RObject ref = frame.getOperandStack().getRefFromTop(method.getArgSlotCount()-1);
            if(ref == null){
                if(methodRef.getName().equals("println")) {
                    _println(frame.getOperandStack(), methodRef.getDescriptor());
                    return;
                }
                throw new RuntimeException("NullPointerException");
            }

            //弄清楚refClass和currentClass以及methodClass的区别
            RClass refClass = ref.getRClass();
            if(method.isProtected()
                    && methodClass.isSuperClassOf(currentClass)
                    && methodClass.isSamePackage(currentClass)
                    && refClass != currentClass
                    && !refClass.isSubClassOf(currentClass)){
                throw new RuntimeException("IllegalAccessError");
            }

            RMethod methodToBeInvoked = MethodLookup.lookupMethodInClass(refClass,
                    methodRef.getName(), methodRef.getDescriptor());

            if(methodToBeInvoked == null || methodToBeInvoked.isAbstract()){
                throw new RuntimeException("AbstractMethodError");
            }

            MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
        }

        // hack!
        private void _println(OperandStack stack, String descriptor){
            switch (descriptor){
                case "(Z)V":
                    printf(String.format("%b\n", stack.popInt() != 0));
                    break;
                case "(C)V":
                    printf(String.format("%c\n", stack.popInt()));
                    break;
                case "(B)V":
                    printf(String.format("%d\n", stack.popInt()));
                    break;
                case "(S)V":
                    printf(String.format("%d\n", stack.popInt()));
                    break;
                case "(I)V":
                    printf(String.format("%d\n", stack.popInt()));
                    break;
                case "(J)V":
                    printf(String.format("%d\n", stack.popLong()));
                    break;
                case "(F)V":
                    printf(String.format("%f\n", stack.popFloat()));
                    break;
                case "(D)V":
                    printf(String.format("%f\n", stack.popDouble()));
                    break;
                default:
                    throw new RuntimeException("println: "+descriptor);
            }
            // TODO 下面这个到底要不要？要了就会报错
            // stack.popRef();
        }

        void printf(String s){
            System.out.print(s);
        }
    }

    public static class INVOKE_INTERFACE implements Instruction{

        Uint16 index;
        //count uint8
        //zero uint8

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readUint16();
            reader.readUint8(); //count
            reader.readUint8(); //zero
        }

        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            InterfaceMethodRef iMethodRef = cp.getConstant(index.value());
            RMethod iMethod = iMethodRef.resolveInterfaceMethod();
            if(iMethod.isStatic() || iMethod.isPrivate()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }

            RObject ref = frame.getOperandStack().getRefFromTop(iMethod.getArgSlotCount()-1);
            if(ref == null){
                throw new RuntimeException("NullPointerException");
            }
            RClass refClass = ref.getRClass();
            if(!refClass.isImplements(iMethodRef.resolveClass())){
                throw new RuntimeException("IncompatibleClassChangeError");
            }

            RMethod methodToBeInvoked = MethodLookup.lookupMethodInClass(refClass,
                    iMethodRef.getName(), iMethodRef.getDescriptor());
            if(methodToBeInvoked == null || methodToBeInvoked.isAbstract()){
                throw new RuntimeException("AbstractMethodError");
            }
            if(!methodToBeInvoked.isPublic()){
                throw new RuntimeException("IllegalAccessError");
            }

            MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
        }

        @Override
        public String toString() {
            return Instruction.string(this)+" #"+index.value();
        }

    }

}
