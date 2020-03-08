import java.util.*;

class Exam {
    
    public static List<Integer> updateRow(char[][] resArr, int rowNo, int c1, int c2, int strIndex, int sizeOfString, char[] strArray){
        List<Integer> tempArr = new ArrayList<>();
        int i =0;
        if(c1<c2){
            for(i = c1; i<=c2; i++){
                if(strIndex == sizeOfString) strIndex = 0;
                if (resArr[rowNo][i] != '\u0000') break;
                resArr[rowNo][i] = strArray[strIndex];
                strIndex ++;
            }
            tempArr.add(i-1); // column to start with 5
            tempArr.add(rowNo+1); // row to start with 1
            tempArr.add(strIndex); //index to start with
            return tempArr;
        }else if(c1>c2){//5 4 0
            for(i = c1; i>=c2; i--){
                if(strIndex == sizeOfString) strIndex = 0;
                if (resArr[rowNo][i] != '\u0000') break;
                resArr[rowNo][i] = strArray[strIndex];
                strIndex++;
            }
            tempArr.add(i+1); // column to start with //0
            tempArr.add(rowNo-1); // row to start with 1 //4
            tempArr.add(strIndex); //index to start with // 
            return tempArr;
        }
        return tempArr;
    }
    
    public static List<Integer> updateColumn(char[][] resArr, int columnNo, int r1, int r2, int strIndex, int sizeOfString, char[] strArray){
        List<Integer> tempArr = new ArrayList<>();
        int i =0 ;
        if(r1<r2){
            for(i = r1; i<=r2; i++){
                if(strIndex == sizeOfString) strIndex = 0;
                if(resArr[i][columnNo] != '\u0000') break;
                resArr[i][columnNo] = strArray[strIndex];
                strIndex++;
            }
            tempArr.add(i-1); //row to start with 
            tempArr.add(columnNo-1); // column to start with
            tempArr.add(strIndex);
            return tempArr;
        }else if(r1>r2){//0 4 0
            for(i = r1; i>=r2; i--){
                if(strIndex == sizeOfString) strIndex = 0;
                if(resArr[i][columnNo] != '\u0000') break;
                resArr[i][columnNo] = strArray[strIndex];
                strIndex++;
            }
            tempArr.add(i+1); //row to start with //1
            tempArr.add(columnNo+1); // column to start with 1
            tempArr.add(strIndex);
            return tempArr;
        }
        return tempArr;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int p = 0; p < t; p++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int sizeOfString = sc.nextInt();
            sc.nextLine();
            char[] strArray = sc.nextLine().toCharArray();
            
            char[][] resArray = new char[m][n];
            
            int totalUpdation = 0;
            boolean updateRow = true;
            boolean updateRowInForwardDir = true;
            boolean updateColInForwardDir = true;
            int rowNo = 0, c1 = 0, c2 = n-1, colNo = n, r1 = 0, r2 = 0, strIndex = 0;
            
            while(totalUpdation < (m*n)){
                if(updateRow){
                    if(updateRowInForwardDir){
                        List<Integer> resList = updateRow(resArray,rowNo,c1,c2,strIndex,sizeOfString,strArray);
                        totalUpdation += resList.get(0)-c1+1;
                        colNo = resList.get(0); //col to start with 
                        r1 = resList.get(1); // row to start with
                        r2 = m-1;
                        strIndex = resList.get(2);
                        updateRow = false;
                        updateColInForwardDir = true;
                    }else{
                        List<Integer> resList = updateRow(resArray,rowNo,c1,c2,strIndex,sizeOfString,strArray);
                        totalUpdation += c1 - resList.get(0) + 1;
                        colNo = resList.get(0); //col to start with 0
                        r1 = resList.get(1); // row to start with 4
                        r2 = 0;
                        strIndex = resList.get(2);
                        updateRow = false;
                        updateColInForwardDir = false;
                    }
                }else{
                    if(updateColInForwardDir){
                        List<Integer> resList = updateColumn(resArray,colNo,r1,r2,strIndex,sizeOfString,strArray);
                        totalUpdation += resList.get(0) - r1 + 1;
                        rowNo = resList.get(0); //5
                        c1 = resList.get(1); //4
                        c2 = 0;   //0
                        strIndex = resList.get(2);
                        updateRow = true;
                        updateRowInForwardDir = false;
                    }else{
                        List<Integer> resList = updateColumn(resArray,colNo,r1,r2,strIndex,sizeOfString,strArray);
                        totalUpdation += r1- resList.get(0) + 1;
                        rowNo = resList.get(1); //5
                        c1 = resList.get(1); //4
                        c2 = n-1;   //0
                        strIndex = resList.get(2);
                        updateRow = true;
                        updateRowInForwardDir = true;
                    }
                    
                }
            }
            
            for(int i =0; i<m;i++){
                String rs = "";
                for(int j=0; j<n;j++){
                    rs += resArray[i][j];
                }
                System.out.println(rs);
            }
        }

    }
}

// 7
// 4 8 6
// abcdef
// 2 4 3
// abc
// 4 2 3
// def
// 1 8 6
// abcdef
// 8 1 6
// abcdef
// 1 1 3
// abc
// 3 2 3
// fff
// 8 7 5

// abcdefab
// bcdefabc
// abafedcd
// fedcbafe
// abca
// bacb
// de
// ef
// dd
// fe
// abcdefab
// a
// b
// c
// d
// e
// f
// a
// b
// a
// ff
// ff
// ff