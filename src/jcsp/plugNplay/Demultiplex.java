
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

package jcsp.plugNplay;

import jcsp.lang.*;

/**
 * This demultiplexes data from its input channel to its output channel array.
 *
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/Demultiplex1.gif"></p>
 * <H2>Description</H2>
 * <TT>Demultiplex</TT> is a process to convert the single stream of
 * (Integer, Object) messages sent from a {@link Multiplex} process on the other
 * end of its <TT>in</TT> channel back into separate streams (its <TT>out</TT>
 * channels).  It assumes that {@link Multiplex} operates on the same
 * size array of channels as its <TT>out</TT> array.
 * <P>
 * The <I>protocol</I> on the incoming multiplexed stream consists of
 * an <TT>Integer</TT>, that represents the channel identity of the
 * multiplexed data, followed by the multiplexed data.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in</TH>
 *     <TD>java.lang.Integer, java.lang.Object</TD>
 *     <TD>
 *       A channel index followed by the multiplexed data.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out[]</TH>
 *     <TD>java.lang.Object</TD>
 *     <TD>
 *       All channels in this package carry integers.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * @see Multiplex
 * @see Paraplex
 * @see Deparaplex
 *
 * @author P.H. Welch and P.D. Austin
 */
public final class Demultiplex implements CSProcess
{
   /** The input Channel */
   private final ChannelInput in;
   
   /** The output Channels */
   private final ChannelOutput[] out;
   
   /**
    * Construct a new Demultiplex process with the input Channel in and the output
    * Channels out. The ordering of the Channels in the out array make
    * no difference to the functionality of this process.
    *
    * @param in the input channel
    * @param out the output channels
    */
   public Demultiplex(final ChannelInput in, final ChannelOutput[] out)
   {
      this.in = in;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      while (true)
      {
         int index = ((Integer) in.read()).intValue();
         out[index].write(in.read());
      }
   }
}
