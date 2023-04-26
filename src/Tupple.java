class Tuple<T> {
    public T first;
    public T second;
    public T third;

    public Tuple<T> returnTuple(T first,T second,T third) {
        this.first = first;
        this.second = second;
        this.third=third;
        return this;
    }

}