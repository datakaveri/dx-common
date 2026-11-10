package org.cdpg.dx.auth.authentication.handler;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthenticationHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cdpg.dx.auth.authentication.util.BearerTokenExtractor;

public class OptionalMultiIssuerJwtAuthHandler implements AuthenticationHandler {

  private static final Logger LOGGER = LogManager.getLogger(OptionalMultiIssuerJwtAuthHandler.class);
  private final AuthenticationHandler authenticationHandler;

  public OptionalMultiIssuerJwtAuthHandler(AuthenticationHandler authenticationHandler) {
    this.authenticationHandler = authenticationHandler;
  }

  @Override
  public void handle(RoutingContext ctx) {
    String token = BearerTokenExtractor.extract(ctx);
    if (token == null || token.isBlank()) {
      LOGGER.warn("Missing or invalid Authorization header");
      ctx.next();
      return;
    }

    authenticationHandler.handle(ctx);
  }
}
