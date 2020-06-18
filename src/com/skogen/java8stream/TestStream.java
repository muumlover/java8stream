package com.skogen.java8stream;

import org.junit.Test;



import java.util.*;

import java.util.stream.*;

class Person {
    String name;
    int age;
    long steps;

    Person(String _name, int _age, long _steps) {
        this.name = _name;
        this.age = _age;
        this.steps = _steps;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", steps=" + steps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                steps == person.steps &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, steps);
    }

    static Stream<Person> getPeopleStream() {
        return Stream.of(
                new Person("Sam", 22, 9000),
                new Person("Mike", 16, 18600),
                new Person("Allen", 15, 21000),
                new Person("Justin", 25, 12000),
                new Person("Leo", 27, 9500),
                new Person("Cora", 18, 26000)
        );
    }

    static List<Person> getPeopleList() {
        return Arrays.asList(
                new Person("Sam", 22, 9000),
                new Person("Mike", 16, 18600),
                new Person("Allen", 15, 21000),
                new Person("Justin", 25, 12000),
                new Person("Leo", 27, 9500),
                new Person("Cora", 18, 26000)
        );
    }
}


public class TestStream {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 可操作类
     */

    @Test
    public void test1IntStream() {
        System.out.println("整数型 IntStream");
        IntStream intStream = IntStream.of(54, 1, 874, 0, 548, 56, 1, 95, 15, 65, 410);
        System.out.println("结果: " + intStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll).toString());
    }

    @Test
    public void test1LongStream() {
        System.out.println("长整型 LongStream");
        LongStream longStream = LongStream.of(233333333, 0, 485, 687486, 52315874, 1034, -65841532, 32);
        System.out.println("结果: " + longStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll).toString());
    }

    @Test
    public void test1DoubleStream() {
        System.out.println("浮点型 DoubleStream");
        double[] doubleArray = {0.6, 5.2, 84.3, 0.25, -6.5, -9.08, 5.009};
        DoubleStream doubleStream = DoubleStream.of(doubleArray);
        System.out.println("结果: " + doubleStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll).toString());
    }

    @Test
    public void test1StreamString() {
        System.out.println("字符串 Stream<String>");
        Stream<String> stringStream = Stream.of("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("结果: " + stringStream.collect(Collectors.toList()));
    }

    @Test
    public void test1StreamStringArray() {
        System.out.println("字符串数组 Stream<String[]>");
        Stream<String[]> stream = Stream.of(
                new String[]{"L1C1", "L1C2"},
                new String[]{"L2C1", "l2C2"},
                new String[]{"l3C1", "L3C2"}
        );
        System.out.println("结果: " + stream.collect(Collectors.toList()));
    }

    @Test
    public void test1StreamObject() {
        System.out.println("Object 派生类型 Stream<T>");
        Stream<Person> personStream = Stream.of(
                new Person("Sam", 22, 9000),
                new Person("Mike", 16, 18600),
                new Person("Allen", 15, 21000),
                new Person("Justin", 25, 12000),
                new Person("Leo", 27, 9500),
                new Person("Sam", 22, 9000),
                new Person("Cora", 18, 26000)
        );
        System.out.println("结果: " + personStream.collect(Collectors.toList()));
    }

    /**
     * IntStream::of 参数类型只能为基本类型 int，LongStream、DoubleStream 同理
     * Stream 与其内部元素类型对应关系如下
     * IntStream    -- int
     * LongStream   -- long
     * DoubleStream -- double
     * Stream<T>    -- T
     */
    @Test
    public void test1StreamType() {
        int[] intArray = {54, 1, 874, 0, 548, 56, 1, 95, 15, 65, 410}; // 基本数据类型
        IntStream streamInt = IntStream.of(intArray);
        System.out.println(Arrays.toString(streamInt.toArray()));
        Integer[] integerArray = {54, 1, 874, 0, 548, 56, 1, 95, 15, 65, 410}; // 包装类型
//        IntStream stream = IntStream.of(integerArray); //【错误】
        Stream<Integer> streamInteger = Stream.of(integerArray);
        System.out.println(streamInteger.collect(Collectors.toList()));
    }

    /**
     * 获取流的方式
     */

    @Test
    public void test2StreamOf() {
        System.out.println("直接创建 Stream 对象");
        System.out.println("Stream.of(\"abc\", \"\", \"bc\", \"efg\", \"abcd\", \"\", \"jkl\")");
        Stream<String> stream1 = Stream.of("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("结果: " + stream1.collect(Collectors.toList()));
        System.out.println("IntStream.range(1, 5)");
        IntStream stream2 = IntStream.range(1, 5);
        System.out.println("结果: " + stream2.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
        System.out.println("IntStream.rangeClosed(1, 5)");
        LongStream stream3 = LongStream.rangeClosed(1, 5);
        System.out.println("结果: " + stream3.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));


    }

    @Test
    public void test2ArrayStream() {
        System.out.println("通过 Array 生成 Stream 对象");
        System.out.println("Integer[] → Arrays::stream");
        int[] integerArray = {54, 1, 874, 0, 548, 56, 1, 95, 15, 65, 410};
        IntStream stream = Arrays.stream(integerArray);
        System.out.println("结果: " + stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }

    @Test
    public void test2CollectorsStream() {
        System.out.println("通过 Collection 的派生类生成 Stream 对象");
        System.out.println("ArrayList::new → ArrayList::add → Collection::stream");
        List<String> stringList1 = new ArrayList<>();
        stringList1.add("abc");
        stringList1.add("");
        stringList1.add("bc");
        stringList1.add("efg");
        Stream<String> stream3 = stringList1.stream();
        System.out.println("结果: " + stream3.collect(Collectors.toList()));

        System.out.println("String[] → Arrays::asList → Collection::stream");
        String[] strArray = {"abc", "", "bc", "efg", "abcd", "", "jkl"};
        List<String> stringList2 = Arrays.asList(strArray);
        Stream<String> stream4 = stringList2.stream();
        System.out.println("结果: " + stream4.collect(Collectors.toList()));
    }

    @Test
    public void test2ObjectFunc() {
        System.out.println("从其他对象获取");
        System.out.println("new Random().ints().limit(10).mapToObj(Integer::toString)");
        int[] rndIntArray = new Random().ints().limit(10).toArray();
        System.out.println("结果: " + Arrays.toString(rndIntArray));
    }

    /**
     * Terminal 最终操作
     */

    @Test
    public void test3collect1() {
        Integer[] integerArray = {54, 1, 874, 0, 548, 56, 2, 95, 15, 65, 410};
        System.out.println("Stream<T>::collect(ArrayList::new, ArrayList::add, ArrayList::addAll);");
        Stream<Integer> stream = Arrays.stream(integerArray);
        List<Integer> list = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("结果: " + list);
    }

    @Test
    public void test3collect2() {
        Person[] people = new Person[]{
                new Person("Sam", 22, 9000),
                new Person("Mike", 16, 18600),
                new Person("Allen", 15, 21000),
                new Person("Justin", 25, 12000),
                new Person("Leo", 27, 9500),
                new Person("Cora", 18, 26000)
        };
        System.out.println("Stream<T>::collect(Collectors.toList());");
        Stream<Person> stream = Arrays.stream(people);
        List<Person> list = stream.collect(Collectors.toList());
        System.out.println("结果: " + list);
    }


    /**
     * 基本数据类型对应的 Stream 使用 collect方法时，只能使用三个参数的 collect 函数，将 intArray 转为 List<Integer>。
     * 是因为 IntStream 内的元素类型只能为 int （基本数据类型），而基本数据类型无法放入到 List 中，需要包装后才能放入。
     */
    @Test
    public void test3collect3() {
        int[] intArray = {54, 1, 874, 0, 548, 56, 2, 95, 15, 65, 410};
        System.out.println("IntStream::collect(ArrayList::new, ArrayList::add, ArrayList::addAll);");
        IntStream stream = IntStream.of(intArray);
        List<Integer> intList = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("结果: " + intList);
    }

    @Test
    public void test3reduce() {
        System.out.println("Stream<T>.count();");
        System.out.println("Stream<T>.mapToLong(e -> 1L).sum();");
        System.out.println("Stream<T>.mapToLong(e -> 1L).reduce(0, Long::sum);");
        System.out.println("结果: " + Person.getPeopleStream().count());
        Person max = Person.getPeopleStream().max(Comparator.comparingLong(x -> x.steps)).orElse(null);
        System.out.println("Stream<T>.max(Comparator.comparingLong(x -> x.steps)).orElse(null);");
        System.out.println("结果: " + max);
        Person min = Person.getPeopleStream().min(Comparator.comparingInt(x -> x.age)).orElse(null);
        System.out.println("Stream<T>.min(Comparator.comparingInt(x -> x.age)).orElse(null);");
        System.out.println("结果: " + min);
    }


    @Test
    public void test3toArray() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        Stream<String> stream = stringList.stream();
        System.out.println("Stream<T>.toArray();");
        System.out.println("结果: " + Arrays.toString(stream.toArray()));
    }


    @Test
    public void test3forEach() {
        System.out.println("仅对并行处理的流有区别，串行的流本身会保持有序");
        Stream<Person> stream = Person.getPeopleList().parallelStream();
        System.out.println("Stream<T>.forEach(System.out::println);");
        System.out.println("结果: ");
        stream.forEach(System.out::println);
        Stream<Person> parallelStream = Person.getPeopleList().parallelStream();
        System.out.println("parallel: Stream<T>.forEachOrdered(System.out::println);");
        System.out.println("结果: ");
        parallelStream.forEachOrdered(System.out::println);
    }

    @Test
    public void test3iterator() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        Stream<String> stream = stringList.stream();
        Iterator<String> stringIter = stream.iterator();
        while (stringIter.hasNext()) {
            String s = stringIter.next();
            System.out.print(String.format("\"%s\", ", s));
        }
        System.out.println();
    }

    @Test
    public void test3anyMatch() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        Stream<String> stream1 = stringList.stream();
        System.out.println("stream.anyMatch(String::isEmpty);");
        boolean res1 = stream1.anyMatch(String::isEmpty);
        System.out.println("结果: " + res1);
        Stream<Person> stream2 = Person.getPeopleStream();
        System.out.println("stream.allMatch(String::isEmpty);");
        boolean res2 = stream2.allMatch(x -> x.steps > 5000);
        System.out.println("结果: " + res2);
        Stream<String> stream3 = stringList.stream();
        System.out.println("stream.noneMatch(String::isEmpty);");
        boolean res3 = stream3.noneMatch(String::isEmpty);
        System.out.println("结果: " + res3);
    }

    /**
     * Intermediate 中间操作
     */
    @Test
    public void test3map() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> upper = stringList.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("初始值: " + stringList);
        System.out.println("转大写: " + upper);
    }


    @Test
    public void test3flatMap() {
        Stream<String[]> stream = Stream.of(
                new String[]{"L1C1", "L1C2"},
                new String[]{"L2C1", "l2C2"},
                new String[]{"l3C1", "L3C2"}
        );
        stream.flatMap(Arrays::stream).forEach(System.out::println);
    }


    @Test
    public void test3filter() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = stringList.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
        System.out.println("过滤前: " + stringList);
        System.out.println("过滤后: " + filtered);
    }

    @Test
    public void test3limit() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = stringList.stream().limit(5).collect(Collectors.toList());
        System.out.println("origin: " + stringList);
        System.out.println("limit: " + filtered);
    }

    @Test
    public void test3skip() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = stringList.stream().skip(5).collect(Collectors.toList());
        System.out.println("origin: " + stringList);
        System.out.println("skip: " + filtered);
    }

    @Test
    public void test3distinct() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "bc", "jkl");
        List<String> filtered = stringList.stream().distinct().collect(Collectors.toList());
        System.out.println("去重前: " + stringList);
        System.out.println("去重后: " + filtered);
    }

    @Test
    public void test3unordered() {
        System.out.println();
        Stream.of(5, 1, 2, 6, 3, 7, 4).unordered().forEach(System.out::print);
        System.out.println();
        Stream.of(5, 1, 2, 6, 3, 7, 4).unordered().forEach(System.out::print);
        System.out.println();
        Stream.of(5, 1, 2, 6, 3, 7, 4).unordered().parallel().forEach(System.out::print);
        System.out.println();
        Stream.of(5, 1, 2, 6, 3, 7, 4).unordered().parallel().forEach(System.out::print);
        System.out.println();
        Stream.of(5, 1, 2, 6, 3, 7, 4).parallel().forEach(System.out::print);
        System.out.println();
        Stream.of(5, 1, 2, 6, 3, 7, 4).parallel().forEach(System.out::print);
        System.out.println();

        List<Person> peopleList = Person.getPeopleList();

        peopleList.parallelStream().limit(3).forEach(System.out::println);
        System.out.println();
        peopleList.parallelStream().limit(3).forEach(System.out::println);
        System.out.println();
        peopleList.stream().parallel().limit(3).forEach(System.out::println);
        System.out.println();
        peopleList.stream().parallel().limit(3).forEach(System.out::println);
        System.out.println();
        peopleList.stream().limit(3).forEach(System.out::println);
        System.out.println();
        peopleList.stream().limit(3).forEach(System.out::println);
        System.out.println();
        peopleList.stream().unordered().parallel().limit(3).forEach(System.out::println);
        System.out.println();
        peopleList.stream().unordered().parallel().limit(3).forEach(System.out::println);
    }

    @Test
    public void test4() {
        Random random = new Random();
        ArrayList<Person> peopleList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 2000000; i++) {
            peopleList.add(new Person(getRandomString(5),
                    random.nextInt(100), random.nextLong()));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("生成数据耗时: " + (endTime - startTime) + "ms");
        int i = 0;
        while (i++ < 4) {
            {
                startTime = System.currentTimeMillis();
                List<Person> adultList = new ArrayList<>();
                for (Person p : peopleList) {
                    if (p.getAge() >= 18) {
                        adultList.add(p);
                    }
                }
                Collections.sort(adultList, new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return Long.compare(p1.getSteps(), p2.getSteps());
                    }
                });
                endTime = System.currentTimeMillis();
                if (i > 1) System.out.println("结果: " + (long) adultList.size());
            }
            if (i > 1) System.out.println("普通方式耗时: " + (endTime - startTime) + "ms");
            {
                startTime = System.currentTimeMillis();
                List<Person> adultList = peopleList.stream()
                        .filter(x -> x.getAge() >= 18)
                        .sorted(Comparator.comparingLong(Person::getSteps))
                        .collect(Collectors.toList());
                endTime = System.currentTimeMillis();
                if (i > 1) System.out.println("结果: " + (long) adultList.size());
            }
            if (i > 1) System.out.println("Stream（串行）方式耗时: " + (endTime - startTime) + "ms");
            {
                startTime = System.currentTimeMillis();
                List<Person> adultList = peopleList.stream().unordered()
                        .filter(x -> x.getAge() >= 18)
                        .sorted(Comparator.comparingLong(Person::getSteps))
                        .collect(Collectors.toList());
                endTime = System.currentTimeMillis();
                if (i > 1) System.out.println("结果: " + (long) adultList.size());
            }
            if (i > 1) System.out.println("Stream（串行 unordered）方式耗时: " + (endTime - startTime) + "ms");
            {
                startTime = System.currentTimeMillis();
                List<Person> adultList = peopleList.parallelStream()
                        .filter(x -> x.getAge() >= 18)
                        .sorted(Comparator.comparingLong(Person::getSteps))
                        .collect(Collectors.toList());
                endTime = System.currentTimeMillis();
                if (i > 1) System.out.println("结果: " + (long) adultList.size());
            }
            if (i > 1) System.out.println("Stream（并行）方式耗时: " + (endTime - startTime) + "ms");
            {
                startTime = System.currentTimeMillis();
                List<Person> adultList = peopleList.parallelStream().unordered()
                        .filter(x -> x.getAge() >= 18)
                        .sorted(Comparator.comparingLong(Person::getSteps))
                        .collect(Collectors.toList());
                endTime = System.currentTimeMillis();
                if (i > 1) System.out.println("结果: " + (long) adultList.size());
            }
            if (i > 1) System.out.println("Stream（并行 unordered）方式耗时: " + (endTime - startTime) + "ms");
        }
    }

    @Test
    public void test() {

    }


}
