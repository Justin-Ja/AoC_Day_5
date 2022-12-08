import java.util.Scanner;


public class Runner {
    char[] one = new char[100];
    char[] two = new char[100];
    char[] three = new char[100];
    char[] four = new char[100];
    char[] five = new char[100];
    char[] six = new char[100];
    char[] seven = new char[100];
    char[] eight = new char[100];
    char[] nine = new char[100];

    int[] stackPointers = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    char[][] stacks = {one, two, three, four, five, six, seven, eight, nine};

    public static void main(String args[]){
        Load load = new Load("input.txt", "assets"); 
        String input = load.readTheFile();
        Scanner scan = new Scanner(input);
        String line = "";
        String[] helper = new String[100];
        char[][] ch = new char[10][20];
        char[] tempStorage = new char[100];
        Runner main = new Runner();
        int height = 0, k = 0, move = 0, fromStack = 0, toStack = 0;
        
        //Setup
        while(scan.hasNextLine()){
            line = scan.nextLine();
            if(line.equals("")){
                break;
            } 
            line = line + "\n";
            ch[height] = line.toCharArray();
            height++;
        }
        height = height - 2;
        for(int i = height; i >= 0; i--){
            for(int j = 1; j < ch[i].length; j += 4){
                if(ch[i][j] != ' '){
                    main.push(ch[i][j], k);
                }
                k++;
            }
            k = 0;
        }

        //Actual instructions
        while(scan.hasNextLine()){
            line = scan.nextLine();
            line = line.replaceAll("[a-z]","").trim(); 
            line = line.replaceAll("  ", ",");
            helper = line.split(",");
            move = Integer.parseInt(helper[0]);
            fromStack = Integer.parseInt(helper[1]) - 1;
            toStack = Integer.parseInt(helper[2]) - 1;
            
            //Part 1 ************************************************************************
            // for(int i = move; i > 0; i++){
            //     main.push(main.pop(fromStack) , toStack);
            // }
            //******************************************************************************** 

            //Part 2
            if(move == 1){
                main.push(main.pop(fromStack) , toStack);
            } else{
                for(int i = move; i > 0; i--){
                    tempStorage[i] = main.pop(fromStack);
                }
                for(int i = 1; i <= move; i++){
                    main.push(tempStorage[i], toStack);
                }
            }
        }
            
        for(int i = 0; i < 9; i++){
            System.out.println("Top of stack value: " + main.pop(i));
        }
        scan.close();

    }

    public void push(char input, int stackNum){
        stacks[stackNum][++stackPointers[stackNum]] = input;
    }

    public char pop (int stackNum){
        if(!isEmpty(stackNum)){
            return stacks[stackNum][stackPointers[stackNum]--];
        } else {
            System.out.println("Error underflow from " + stackNum);
            System.exit(-1);
            return 'L';
        }
    }

    public char peek (int stackNum){
        return stacks[stackNum][stackPointers[stackNum]];
    }

    public boolean isEmpty(int stackNum){
        if(stackPointers[stackNum] == -1){
            return true;
        } else {
            return false;
        }
    }
}
