package eu.qwan.editrain.domain.builders;

public interface Builder<T> {
    static <T> T build(Builder<T> builder) {
        return builder.build();
    }

    T build();
}
