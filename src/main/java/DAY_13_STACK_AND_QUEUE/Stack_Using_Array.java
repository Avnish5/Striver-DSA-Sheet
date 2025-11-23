package DAY_13_STACK_AND_QUEUE;

public class Stack_Using_Array {
    private int data[];
    private int top;

    Stack_Using_Array()
    {
        data=new int[3];
        top=-1;
    }

    Stack_Using_Array(int capacity)
    {
        data=new int[capacity];
        top=-1;
    }

    public boolean isEmpty()
    {
        return (top==-1);
    }

    public int size()
    {
        return top+1;
    }

    public int top() throws StackEmptyException {
        if(isEmpty())
        {
            StackEmptyException e=new StackEmptyException();
            throw  e;
        }

        return data[top];
    }

    public void push(int ele) throws StackFullException {

        if(size()==data.length)
        {
            doubleCapacity();
        }
        top++;
        data[top]=ele;
    }

    private void  doubleCapacity()
    {
        int temp[]=data;
        data=new int[2*temp.length];

        for(int i=0;i<=top;i++)
            data[i] = temp[i];
    }

    public int pop()
    {
        int temp=data[top];
        top--;
        return temp;
    }

}
