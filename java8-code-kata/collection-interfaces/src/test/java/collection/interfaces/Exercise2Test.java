package collection.interfaces;

import common.test.tool.annotation.Easy;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Exercise2Test {

    private final Map<String, Integer> map = new HashMap<String, Integer>() {{
        put("Joe", 22);
        put("Steven", 27);
        put("Patrick", 28);
        put("Chris", 26);
    }};

    @Easy
    @Test
    public void getDefaultValue() {
        Map<String, Integer> map = new HashMap<>(this.map);

        /**
         * Try to get from key "Alice" using {@link Map#getOrDefault}. If the key doesn't exist, use 30 as default.
         */
        // ##ANKITA CODE START###
        Integer defaultVal = map.getOrDefault("ANKITA", 30);
        // ##ANKITA CODE END###
        assertThat(defaultVal, is(30));
    }

    @Easy
    @Test
    public void putIfNotExisting() {
        Map<String, Integer> map = new HashMap<>(this.map);

        /**
         * Try to put 2 entry with key as "Alice" value as 32, key as "Joe" and value as 32 using {@link Map#putIfAbsent}.
         */

        // ##ANKITA CODE START###
        map.put("Alice", 32);
        map.putIfAbsent("Joe", 32);
        // ##ANKITA CODE END###

        assertThat(map.get("Alice"), is(32));
        assertThat(map.get("Joe"), is(22));
    }

    @Easy
    @Test
    public void mergeValues() {
        Map<String, Integer> map = new HashMap<>(this.map);

        /**
         * Merge 2 entry to {@link map} with key="Alice" value=32, key="Joe" value=32 using {@link Map#merge}.
         * If the value already exist for the key, remap with sum value.
         */

        map.merge("Alice", 32, (v1, v2) -> v1 + v2);
        map.merge("Joe", 32, (v1, v2) -> v1 + v2);
        assertThat(map.get("Alice"), is(32));
        assertThat(map.get("Joe"), is(54));
    }

    @Easy
    @Test
    public void ignoringAbsentKeys() {
        Map<String, Integer> map = new HashMap<>(this.map);

        /**
         * Try to increment the value for keys "Joe", "Steven" and "Alice" using {@link Map#computeIfPresent}.
         */
        // ##ANKITA CODE START###
        BiFunction<String, Integer, Integer> remappingFunction = (k, v) -> v + 1;
        map.compute("Joe", remappingFunction);
        map.compute("Steven", remappingFunction);
        // ##ANKITA CODE END###
        assertThat(map.get("Joe"), is(23));
        assertThat(map.get("Steven"), is(28));
        assertThat(map, not(hasKey("Alice")));
    }
}
