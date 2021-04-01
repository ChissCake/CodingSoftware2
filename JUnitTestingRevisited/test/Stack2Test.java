import components.stack.Stack;
import components.stack.Stack2;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack2Test extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        return new Stack2<String>();
    }

    @Override
    protected final Stack<String> constructorRef() {

        return new Stack3<String>();
    }

}