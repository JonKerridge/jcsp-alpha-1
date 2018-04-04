
//////////////////////////////////////////////////////////////////////
//                                                                  //
//  JCSP ("CSP for Java") Libraries                                 //
//  Copyright (C) 1996-2018 Peter Welch, Paul Austin and Neil Brown //
//                2001-2004 Quickstone Technologies Limited         //
//                2005-2018 Kevin Chalmers                          //
//                                                                  //
//  You may use this work under the terms of either                 //
//  1. The Apache License, Version 2.0                              //
//  2. or (at your option), the GNU Lesser General Public License,  //
//       version 2.1 or greater.                                    //
//                                                                  //
//  Full licence texts are included in the LICENCE file with        //
//  this library.                                                   //
//                                                                  //
//  Author contacts: P.H.Welch@kent.ac.uk K.Chalmers@napier.ac.uk   //
//                                                                  //
//////////////////////////////////////////////////////////////////////

package jcsp.win32;

import java.io.*;

/**
 * Common file to ensure that the DLL gets loaded. All files in the Win32 package that declare native
 * methods from this DLL should make a call to <code>LoadDLL.go ()</code> from their static initializers.
 *
 * @deprecated no longer required with more modern windows operating systems
 * @author Quickstone Technologies Limited
 */
class LoadDLL
{
    /**
     * Indicates whether the library has already been loaded.
     */
    private static boolean loaded = false;

    /**
     * Loads the library if it hasn't already been loaded.
     */
    static synchronized void go()
    {
        if (loaded)return;
        File f = new File("./org.win32.dll");
        System.load(f.getAbsolutePath());
        loaded = true;
    }
}
