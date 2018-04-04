
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

package jcsp.net.remote;

import jcsp.win32.*;

/**
 * <p>Wraps up the <code>SpawnerService</code> as an NT service. To install the service, register the
 * path <code>java jcsp.net.remote.SpawnerServiceNT</code> with the service name
 * <code>JCSP.NET:SpawnerService</code>.</p>
 *
 * @deprecated please use package net2 instead
 * @author Quickstone Technologies Limited
 */
public class SpawnerServiceNT extends NTService
{
   private SpawnerService svc;
   
   /**
    * Starts the service by calling <code>SpawnerService.main</code>.
    */
   protected void startService()
   {
      svc = SpawnerService.construct(new String[0]);
      svc.run();
      // will block
   }
   
   /**
    * Terminates the spawner.
    */
   protected void stopService()
   {
      svc.stop();
   }
   
   /**
    * Creates the service instance.
    */
   private SpawnerServiceNT()
   {
      super("JCSP.NET:SpawnerService");
   }
   
   /**
    * Creates a service instance and sets it running.
    */
   public static void main(String[] args)
   {
      new SpawnerServiceNT().run();
   }
}