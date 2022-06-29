package com.revature.util;

import java.util.Scanner;

public class Util {

	public static Scanner in = new Scanner(System.in);

	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void pause() {
		System.out.print("Press \"ENTER\" to continue...");
		Util.in.nextLine();
		Util.clear();
	}

	public static void print(Object o) {
		System.out.print(o);
	}

	public static void println(Object o) {
		System.out.println(o);
	}

	public static void println() {
		System.out.println();
	}

	public static void println(int n) {
		System.out.print(String.format("%0" + n + "d", 0).replace("0", "\n"));
	}

	public static void banner() {
		Table.row("    ////             //         //        //    ////////////      ////    ");
		Table.row("  ///  ///          ////        ///      ///    ////////////    ///  ///  ");
		Table.row(" ///    ///        //  //       ////    ////    //             ///    /// ");
		Table.row("//                //    //      // //  // //    //             ///     ///");
		Table.row("//               ///    ///     //  ////  //    ///////        ////       ");
		Table.row("//     /////    ////////////    //   //   //    ///////          //////   ");
		Table.row("//     /////    ////////////    //        //    //                    /// ");
		Table.row(" ///     ///    //        //    //        //    //            ///     /// ");
		Table.row("  ///  ///      //        //    //        //    ////////////   ///   ///  ");
		Table.row("   //////       //        //    //        //    ////////////      ////    ");
		println();
	}

	public static void hr() {
		System.out.println(String.format("%0" + 80 + "d", 0).replace("0", "*"));
	}

	public static boolean isDouble(String s) {
		String regex = ".0123456789";
		for (char c : s.toCharArray()) {
			if (regex.indexOf(c) == -1)
				return false;
		}
		return true;
	}

	public static boolean isInt(String s) {
		String regex = "0123456789";
		for (char c : s.toCharArray()) {
			if (regex.indexOf(c) == -1)
				return false;
		}
		return true;
	}

	public static void invalid() {
		Util.println("Invalid input.");
		pause();
	}

	public static void exit() {
		System.out.println("Goodbye!");
		pause();
		Util.in.close();
		System.exit(0);
	}

}
