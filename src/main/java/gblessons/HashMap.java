package gblessons;

public class HashMap<K, V> {

    //region Публичные методы

    public V put(K key, V value){
        if (buckets.length * LOAD_FACTOR <= size){
            recalculate();
        }
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if(bucket == null){
            bucket = new Bucket();
            buckets[index] = bucket;
        }

        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;

        V buf = (V)bucket.add(entity);

        if(buf == null){
            size++;
        }
        return  buf;

    }

    //endregion

    //region Служебные методы

    private void recalculate(){
        size = 0;
        Bucket<K,V>[] old = buckets;
        buckets  = new Bucket[old.length * 2];
        for (int i = 0; i < old.length ; i++) {
            Bucket<K,V> bucket = old[i];
            if(bucket != null){
                Bucket.Node node = bucket.head;
                while(node != null){
                    put((K) node.value.key, (V)node.value.value);
                    node = node.next;
                }
            }
        }
    }
    private int calculateBucketIndex(K key){
        return Math.abs(key.hashCode()) % buckets.length;
    }

    //endregion

    //region Конструкторы
    public HashMap(){
        buckets = new Bucket[INIT_BUCKET_COUNT];
    }

    public HashMap(int initCount){
        buckets = new Bucket[initCount];
    }


    //endregion

    //region Вспомогательные структуры
    class Entity {

        K key;

        V value;
    }

    class Bucket <K, V>{

        private Node head;

        class Node {

            Node next;

            Entity value;


        }
        public V add(Entity entity){
            Node node = new Node();
            node.value = entity;
            if(head == null){
                head = node;
                return null;
            }

            Node currentNode = head;
            while(true){
                if(currentNode.value.key.equals(entity.key)){
                    V buf = (V)currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return buf;
                }
                if(currentNode.next != null){
                    currentNode = currentNode.next;
                } else {
                    currentNode.next = node;
                    return null;
                }
            }
        }
    }
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < buckets.length ; i++) {
            Bucket<K, V> bucket = buckets[i];
            if (bucket != null) {
                Bucket.Node node = bucket.head;
                while(node != null){
                    stringBuilder.append(node.value.key).append(", ").append(node.value.value);
                    stringBuilder.append("\n");
                    node = node.next;
                }
            }
        }
        return stringBuilder.toString();
    }
    //endregion

    //region Поля
    private Bucket[] buckets;
    private int size;

    //endregion

    //region Константы
    private static final int INIT_BUCKET_COUNT = 16;
    private  static final double LOAD_FACTOR = 0.5;

    //endregion
}
