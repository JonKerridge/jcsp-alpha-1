
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

package jcsp.net.dynamic;

import java.io.Serializable;
import jcsp.net.*;
import jcsp.util.filter.FilteredChannelInput;

/**
 * A migratable input channel end that can be used as a guard within an <code>Alternative</code>.
 *
 * @deprecated please use package net2 instead
 * @author Quickstone Technologies Limited
 */
public abstract class MigratableAltingChannelInput 
extends NetAltingChannelInput 
implements MigratableChannelInput, FilteredChannelInput, Serializable
{
   /**
    * Constructs a new <code>MigratableAltingChannelInput</code> for the underlying channel.
    *
    * @param channel the underlying channel.
    */
   protected MigratableAltingChannelInput(NetAltingChannelInput channel)
   {
      super(channel);
   }
   
   /**
    * Constructs a new <code>MigratableAltingChannelInput</code>.
    */
   protected MigratableAltingChannelInput()
   {
      super();
   }
}