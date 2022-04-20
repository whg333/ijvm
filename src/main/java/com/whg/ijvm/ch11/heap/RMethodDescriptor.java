package com.whg.ijvm.ch11.heap;

import com.whg.ijvm.ch11.classfile.uint.Uint8;

import java.util.ArrayList;
import java.util.List;

public class RMethodDescriptor {

    List<String> parameterTypes = new ArrayList<>();
    String returnType;

    public String[] getParameterTypes() {
        return parameterTypes.toArray(new String[0]);
    }

    public String getReturnType() {
        return returnType;
    }

    public static RMethodDescriptor parse(String descriptor){
        //TODO impl
        return new MethodDescriptorParser().parse(descriptor);
    }

    private static class MethodDescriptorParser {

        String descriptor; // for debug

        byte[] raw;
        int offset;
        RMethodDescriptor parsed;

        RMethodDescriptor parse(String descriptor){
            this.descriptor = descriptor;
            raw = descriptor.getBytes();
            parsed = new RMethodDescriptor();

            startParams();
            parseParamTypes();
            endParams();
            parseReturnType();
            finish();

            return parsed;
        }

        private void startParams(){
            if(readUint8() != '('){
                causePanic();
            }
        }

        private void parseParamTypes(){
            for(;;) {
                String t = parseFieldType();
                if(!t.equals("")){
                    parsed.addParameterType(t);
                } else {
                    break;
                }
            }
        }

        private String parseFieldType(){
            switch (readUint8()) {
                case 'B':
                    return "B";
                case 'C':
                    return "C";
                case 'D':
                    return "D";
                case 'F':
                    return "F";
                case 'I':
                    return "I";
                case 'J':
                    return "J";
                case 'S':
                    return "S";
                case 'Z':
                    return "Z";
                case 'L':
                    return parseObjectType();
                case '[':
                    return parseArrayType();
                default:
                    unreadUint8();
                    return "";
            }
        }

        private String parseObjectType(){
            String unread = readString(offset);
            int semicolonIndex = unread.indexOf(';');
            if(semicolonIndex == -1){
                causePanic();
                return "";
            } else {
                int objStart = offset - 1;
                int objEnd = offset + semicolonIndex + 1;
                offset = objEnd;
                String descriptor = readString(objStart, objEnd);
                return descriptor;
            }
        }

        private String parseArrayType(){
            int arrStart = offset - 1;
            parseFieldType();
            int arrEnd = offset;

            // TODO 需要确认[arrStart:arrEnd]在go的范围区间
            // descriptor := self.raw[arrStart:arrEnd]
            String descriptor = readString(arrStart, arrEnd);
            return descriptor;
        }

        private String readString(int start){
            return readString(start, raw.length);
        }

        private String readString(int start, int end){
            byte[] bytes = new byte[end-start];
            for(int i=0,j=start;j<end;i++,j++){
                bytes[i] = raw[j];
            }
            return new String(bytes);
        }

        private void endParams() {
            if(readUint8() != ')'){
                causePanic();
            }
        }

        private void parseReturnType(){
            if(readUint8() == 'V'){
                parsed.returnType = "V";
                return;
            }

            unreadUint8();
            String t = parseFieldType();
            if(!t.equals("")){
                parsed.returnType = t;
                return;
            }

            causePanic();
        }

        private void finish(){
            if(offset != raw.length){
                causePanic();
            }
        }

        private void causePanic(){
            throw new RuntimeException("BAD descriptor: " + new String(raw));
        }

        private short readUint8(){
            byte b = raw[offset];
            offset++;
            return Uint8.value(b);
        }

        private void unreadUint8(){
            offset--;
        }

    }

    private void addParameterType(String t) {
        parameterTypes.add(t);
    }

}
