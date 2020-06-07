package collection.interfaces;

import common.test.tool.annotation.Easy;
import common.test.tool.dataset.ClassicOnlineStore;
import common.test.tool.entity.Customer;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Exercise1Test extends ClassicOnlineStore {

    @Easy
    @Test
    public void iterateByForEach() {
        Iterable<Customer> customerIterable = this.mall.getCustomerList();
        List<String> nameList = new ArrayList<>();

        /**
         * Create a {@link Consumer} which represents an operation to add customer's name to {@link nameList} list.
         * Iterate {@link customerIterable} with {@link Iterable#forEach} and use the {@link Consumer}
         * to finish creating the name list.
         */
        // ##ANKITA CODE START###
        Consumer<Customer> consumer = customer -> nameList.add(customer.getName());
        customerIterable.forEach(consumer);
        // ##ANKITA CODE END###

        assertThat(nameList.toString(), is("[Joe, Steven, Patrick, Diana, Chris, Kathy, Alice, Andrew, Martin, Amy]"));
    }

    @Easy
    @Test
    public void whoHaveNoEInYourName() {
        Collection<String> nameCollection =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a {@link Predicate} which predicates if the input string contains "e".
         * Remove elements from {@link nameCollection} which contains "e" using {@link Collection#removeIf}.
         */
        // ##ANKITA CODE START###
        Predicate<String> predicate = x -> x.contains("e");
        nameCollection.removeIf(predicate);
        // ##ANKITA CODE END###
        assertThat(nameCollection.toString(), is("[Patrick, Chris]"));
    }

    @Easy
    @Test
    public void replaceTheElements() {
        List<String> nameList =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a {@link UnaryOperator} which returns given string wrapped with "()".
         * Replace the elements in {@link nameList} with string wrapped with "()" using {@link List#replaceAll} .
         */
        // ##ANKITA CODE START###
        UnaryOperator<String> unaryOperator = s -> "(" + s + ")";
        nameList.replaceAll(unaryOperator);
        // ##ANKITA CODE END###
        assertThat(nameList.toString(), is("[(Joe), (Steven), (Patrick), (Chris)]"));
    }

    @Easy
    @Test
    public void sortByName() {
        List<String> nameList =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a {@link Comparator} to sort the name list by their name's length in ascending order.
         */
        // ##ANKITA CODE START###
        nameList.sort(Comparator.comparing(String::length));
        // ##ANKITA CODE END###
        assertThat(nameList.toString(), is("[Joe, Chris, Steven, Patrick]"));
    }

    @Easy
    @Test
    public void createStream() {
        Collection<String> nameList =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a serial {@link Stream} using {@link Collection#stream}
         * You can learn about {@link Stream} APIs at stream-api module.
         */
        // ##ANKITA CODE START###
        Stream<String> nameStream = nameList.stream();
        // ##ANKITA CODE END###

        assertThat(nameStream.count(), is(4L));
        assertThat(nameStream.isParallel(), is(false));
    }

    @Easy
    @Test
    public void createParallelStream() {
        Collection<String> nameList =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a parallel {@link Stream} using {@link Collection#parallelStream} or {@link Stream#parallel}
         */
        // ##ANKITA CODE START###
        Stream<String> nameParallelStream = nameList.parallelStream();
        // ##ANKITA CODE END###
        assertThat(nameParallelStream.count(), is(4L));
        assertThat(nameParallelStream.isParallel(), is(true));
    }
}