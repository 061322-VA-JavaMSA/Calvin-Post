public class GetInput {

	public static void main(String[] args) {

		int n = 0;

		if(args.length > 0) {
			n = Integer.parseInt(args[0]);
		} else {
			message();
		}

		switch (args.length) {

		//Get random number
		case 1:
		printRandom(n);
		break;

		//Print a string x times
		case 2:
		printString(n, args[1]);
		break;

		//Default case
		default:
		message();
		break;

		}

	}

	public static void printRandom(int x) {
	
		int i = (int)(Math.random()*x) + 1;
		System.out.println(i);

	}

	public static void printString(int x, String s) {

		for(int i=0; i<x; i++) {

			System.out.println(s);
		}

	}

	public static void message() {
	
		System.out.println("Invalid input, use the following formats:");
		System.out.println("GetInput 10");
		System.out.println("GetInput 8 hello");
		System.out.println("GetInput 5 \"Hello World!\"");

	}

}
