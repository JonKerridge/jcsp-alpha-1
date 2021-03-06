
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

package jcsp.net.cns;

/**
 * Thrown if an attempt is made to get a <code>NamedChannelEndManager</code>
 * to destroy a channel that it did not create.
 *
 * @deprecated please use package net2 instead
 * @author Quickstone Technologies Limited
 */
public class WrongFactoryException extends RuntimeException
{
   /**
    * Constructor for WrongFactoryException.
    */
   public WrongFactoryException()
   {
      super();
   }
   
   /**
    * Constructor for WrongFactoryException.
    * @param message
    */
   public WrongFactoryException(String message)
   {
      super(message);
   }
}