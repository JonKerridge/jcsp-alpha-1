
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

package jcsp.net;

import jcsp.util.ChannelDataStore;

/**
 * This interface defines methods for constructing buffered
 * Networked <code>ChannelInput</code> objects.
 *
 * @deprecated please use package net2 instead
 * @author Quickstone Technologies Limited
 */
public interface NetBufferedChannelEndFactory
{
   /**
    * Constructs a <code>NetAltingChannelInput</code> object.
    *
    * @param buffer  the <code>ChannelDataStore</code> to use as a buffer.
    * @return the constructed <code>NetAltingChannelInput</code> object.
    */
   public NetAltingChannelInput createNet2One(ChannelDataStore buffer);
   
   /**
    * Constructs a <code>NetSharedChannelInput</code> object.
    *
    * @param buffer  the <code>ChannelDataStore</code> to use as a buffer.
    * @return the constructed <code>NetSharedChannelInput</code> object.
    */
   public NetSharedChannelInput createNet2Any(ChannelDataStore buffer);
   
}