public class Pair<T>
{
    T p1, p2;
    Pair()
    {
        //default constructor
    }
    void setValue(T a, T b)
    {
        this.p1 = a;
        this.p2 = b;
    }
    Pair getValue()
    {
        return this;
    }

}
