
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

package jcsp.net.tcpip;

import jcsp.win32.*;

/**
 * Wraps up the TCPIPCNSServer as a Windows NT service. To install the service, register the
 * path <code>java jcsp.net.tcpip.TCPIPCNSServerNT</code> with the service name
 * <code>JCSP.NET:TCPIPCNSServer</code>.</p>
 *
 * @deprecated please use package net2 instead
 * @author Quickstone Technologies Limited
 */
public class TCPIPCNSServerNT extends NTService
{
   /**
    * Starts the service by calling <code>TCPIPCNSServer.main</code>.
    */
   protected void startService()
   {
      TCPIPCNSServer2.main(new String[0]);
      // will block
   }
   
   /**
    * Stops the service.
    */
   protected void stopService()
   {
      TCPIPCNSServer2.terminate.out().write(null);
   }
   
   /**
    * Creates a new service instance.
    */
   private TCPIPCNSServerNT()
   {
      super("JCSP.NET:TCPIPCNSServer");
   }
   
   /**
    * Creates a new service instance and sets it running.
    */
   public static void main(String[] args)
   {
      new TCPIPCNSServerNT().run();
   }
}