public class B {
    private OnGeekEventListener mListener; // listener field

    // setting the listener
    public void registerOnGeekEventListener(OnGeekEventListener mListener)
    {
        this.mListener = mListener;
    }

    // my synchronous task
    public void doGeekStuff()
    {

        // perform any operation
        System.out.println("Performing callback before synchronous Task");

        // check if listener is registered.
        if (this.mListener != null) {

            // invoke the callback method of class A
            mListener.onGeekEvent();
        }
    }

    // Driver Function
    public static void main(String[] args)
    {
        B obj = new B();
        OnGeekEventListener mListener = new A();
        obj.registerOnGeekEventListener(mListener);
        obj.doGeekStuff();
    }
}
