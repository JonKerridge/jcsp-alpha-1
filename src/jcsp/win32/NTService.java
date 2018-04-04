
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

    /**
 * <p>Abstract class for declaring an NT service. A Java implementation of a service must declare the
 * <code>startService</code> and <code>stopService</code> methods. <code>startService</code> must run the
 * actual service and must block until the service is ready to terminate. For example it may sit in a loop
 * servicing requests. <code>stopService</code> will be called when <code>startService</code> is expected
 * to stop blocking and return. The <code>stopService</code> method should return promptly. Any time
 * consuming shutdown code should be done asynchronously (for example in the <code>startService</code>
 * thread) before <code>startService</code> returns.</p>
 *
 * <p>For example:</p>
 *
 * <pre>
 * public class MyService extends NTService {
 *   boolean running = true;
 *   protected void startService () {
 *     while (running) {
 *       System.beep ();
 *       Thread.sleep (1000);
 *     }
 *   }
 *   protected void stopService () {
 *     running = false;
 *   }
 *   private MyService () {
 *     super ("MyService");
 *   }
 *   public static void main (String[] args) {
 *     new MyService ().run ();
 *   }
 * }
 * </pre>
 *
 * @deprecated no longer required with more modern windows operating systems
 * @author Quickstone Technologies Limited
 */
public abstract class NTService
{
    /**
     * The name of the service as registered with the NT dispatcher. This really ought to be unique.
     */
    private final String serviceName;

    /**
     * Registers a callback function from the DLL with the NT dispatcher and starts the service.
     */
    private native int startDispatcher();

    /**
     * Blocks inside the DLL until the NT service dispatcher is ready to start the service. This is because
     * the threads allocated by the NT service dispatcher cannot directly call a Java method because there
     * is no valid JNI environment for them.
     */
    private native void waitForStart();

    /**
     * Blocks inside the DLL until the NT service dispatcher is ready to stop the service. This is because
     * the threads allocated by the NT service dispatcher cannot directly call a Java method because there
     * is no valid JNI environment for them.
     */
    private native void waitForStop();

    /**
     * Notifies the NT service dispatcher that the stop has completed and the service has terminated. It is
     * quite possible that the JVM could terminate abruptly (ie do an equivilant of a <code>System.exit</code>)
     * once this method has been called.
     */
    private native void acknowledgeStop();

    /**
     * This must be called prior to any of the other methods to initialize the semaphores maintained internally
     * by the DLL.
     */
    private native void prepareSemaphores();

    /**
     * This will be called when the service is started and must block until the service
     * completes.
     */
    protected abstract void startService();

    /**
     * This will be called when the service is stopped and the <code>startService</code> method must
     * terminate.
     */
    protected abstract void stopService();

    /**
     * Will call the user's <code>startService</code> method when the dispatcher requires it.
     */
    private class StarterThread extends Thread
    {
        public StarterThread()
        {
            setDaemon(true);
            start();
        }

        public void run()
        {
            waitForStart();
            startService();
        }
    }

    /**
     * Will call the user's <code>stopService</code> method when the dispatcher requires it.
     */
    private class StopperThread extends Thread
    {
        public StopperThread()
        {
            setDaemon(true);
            start();
        }

        public void run()
        {
            waitForStop();
            stopService();
            acknowledgeStop();
        }
    }

    /**
     * Ensures that the DLL is loaded.
     */
    static
    {
        LoadDLL.go();
    }

    /**
     * Creates a new NT service wrapper with the given name. This name ought to match the name that is
     * registered with the NT service manager but probably will work if it doesn't. What is more important
     * is that the name is system unique.
     */
    protected NTService(String serviceName)
    {
        this.serviceName = serviceName;
    }

    /**
     * Runs the service, registering it with the NT service dispatcher. If there is a problem this will
     * terminate the JVM with the Windows error code. If all is okay, this will never return. All subsequent
     * processing is done by the allocated <code>StarterThread</code> and <code>StopperThread</code> objects
     * and threads internal to the DLL.
     */
    protected final void run()
    {
        prepareSemaphores();
        new StarterThread();
        new StopperThread();
        int ec = startDispatcher();
        if (ec != 0)
            System.exit(ec);
    }
}
