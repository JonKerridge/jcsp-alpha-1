
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

package jcsp.lang;

    /**
 * <p>This implements a one-to-one object channel
 * for use by a single writer and single reader. Refer to {@link One2OneChannel} for a
 * description of this behaviour.</p>
 *
 * <p>Additionally, this channel supports a <code>reject</code> operation. The reader may call
 * the reject method to force any current writer to abort with a
 * <code>ChannelDataRejectedException</code>. Subsequent read and write attempts will immediately cause a
 * <code>ChannelDataRejectedException</code>.</p>
 *
 * <p>Note that the <code>reject</code> operation cannot be called concurrently to a read.</p>
 *
 * @author Quickstone Technologies Limited
 * 
 * @deprecated This channel is superceded by the poison mechanisms, please see {@link PoisonException}.  It remains only because it is used by some of the networking features.
 */
public class RejectableOne2OneChannel        
        implements RejectableChannel
{
	private One2OneChannelImpl innerChannel;
	
    /**
     * Constructs a new channel.
     */
    public RejectableOne2OneChannel()
    {
    	innerChannel = (One2OneChannelImpl) Channel.one2one();
    }
	public RejectableAltingChannelInput inAlt() {
		return new RejectableAltingChannelInputImpl(innerChannel,0);
	}
	
	public RejectableChannelInput in() {
		return new RejectableChannelInputImpl(innerChannel,0);
	}

	public RejectableChannelOutput out() {
		return new RejectableChannelOutputImpl(innerChannel,0);
	}
}
