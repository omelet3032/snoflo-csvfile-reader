package org.snoflo.builder;

import org.snoflo.builder.FinderSystemBuilder.Builder;

public interface CommonNestedBuilder<R, S, V, C> {
    
    Builder repository(R repository);

    Builder service(S service);

    Builder view(V view);

    Builder controller(C controller);
}
