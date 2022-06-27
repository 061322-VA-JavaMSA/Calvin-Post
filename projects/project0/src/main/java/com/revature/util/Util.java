package com.revature.util;

import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {

	public static Scanner in = new Scanner(System.in);
	public static void clear() {
		System.out.flush();
		banner();
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
		hr();
		println();
	}
	
	public static void hr() {
		System.out.println(String.format("%0" + 80 + "d", 0).replace("0", "*"));
	}
	
	public static boolean isDouble(String s) {
		String regex = ".0123456789";
		for(char c : s.toCharArray()) {
			if(regex.indexOf(c) == -1) return false;
		}
		return true;
	}
	
	public static boolean isInt(String s) {
		String regex = "0123456789";
		for(char c : s.toCharArray()) {
			if(regex.indexOf(c) == -1) return false;
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
	
	public static void message(String info) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("");
		a.setHeaderText("");
		a.setContentText(info);
	}

	public static void message(String info, String title) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(title);
		a.setHeaderText("");
		a.setContentText(info);
	}

	public static void message(String info, String title, String header) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(title);
		a.setHeaderText(header);
		a.setContentText(info);
	}
}
