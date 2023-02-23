package ru.meproject.distributify.api.jedis;

import ru.meproject.distributify.api.*;
import ru.meproject.distributify.api.serialization.Deserializer;
import ru.meproject.distributify.api.serialization.Serializer;
import redis.clients.jedis.JedisPool;

import java.util.function.Consumer;

public class JedisDistributifyFactory implements DistributifyFactory {

    private final JedisPool jedisPool;

    public JedisDistributifyFactory() {
        // TODO: config values
        jedisPool = new JedisPool("localhost", 6379);
    }

    @Override
    public <V> DistributedHashMap<V> hashMap(DistributifyDriverConfig config, Serializer<V> serializer, Deserializer<V> deserializer, Consumer<Exception> exceptionHandler) {
        return new JedisDistributedHashMap<>(jedisPool, config, serializer, deserializer, exceptionHandler);
    }

    @Override
    public <V> DistributedSafeHashMap<V> safeHashMap(DistributifyDriverConfig config, Serializer<V> serializer, Deserializer<V> deserializer, Consumer<Exception> exceptionHandler) {
        return new JedisDistributedSafeHashMap<>(jedisPool, config, serializer, deserializer, exceptionHandler);
    }

    @Override
    public DistributedLongCounter longCounter(DistributifyDriverConfig config) {
        return new JedisDistributedLongCounter(jedisPool, config);
    }

}
