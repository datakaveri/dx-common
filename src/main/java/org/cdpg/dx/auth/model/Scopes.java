package org.cdpg.dx.auth.model;

import java.util.Set;

/**
 * The system scope names. Scopes are the unit of authorization — endpoints require
 * scopes, and a principal's effective scope set is checked against them.
 *
 * <p>Use these constants at route declaration sites rather than bare strings.
 */
public final class Scopes {

  private Scopes() {}

  public static final String DATA_ACCESS = "data-access";
  public static final String OWN_ASSET_MANAGEMENT = "own-asset-management";

  public static final String ORG_USER_MANAGEMENT = "org-user-management";
  public static final String ORG_ASSET_MANAGEMENT = "org-asset-management";
  public static final String ORG_ASSET_PUBLISH = "org-asset-publish";
  public static final String ORG_PUBLISHER_MANAGEMENT = "org-publisher-management";

  public static final String ORG_MANAGEMENT = "org-management";
  public static final String ASSET_PUBLISH = "asset-publish";
  public static final String ASSET_MANAGEMENT = "asset-management";
  public static final String USER_MANAGEMENT = "user-management";
  public static final String PUBLISHER_MANAGEMENT = "publisher-management";
  public static final String ROLE_MANAGEMENT = "role-management";

  /**
   * Department-level scopes. Not mapped to any realm role: they are granted at request time to a
   * caller the service resolves as a department admin, and are bounded to that admin's department
   * subtree.
   */
  public static final String DEPT_USER_MANAGEMENT = "dept-user-management";
  public static final String DEPT_ASSET_MANAGEMENT = "dept-asset-management";
  public static final String DEPT_ASSET_PUBLISH = "dept-asset-publish";
  public static final String DEPT_PUBLISHER_MANAGEMENT = "dept-publisher-management";

  /** The full department-admin scope bundle. */
  public static final Set<String> DEPT_ADMIN =
      Set.of(
          DEPT_USER_MANAGEMENT,
          DEPT_ASSET_MANAGEMENT,
          DEPT_ASSET_PUBLISH,
          DEPT_PUBLISHER_MANAGEMENT);

  public static final String COMPUTE_MANAGEMENT = "compute-management";
  public static final String CREDIT_MANAGEMENT = "credit-management";

  public static final Set<String> ALL =
      Set.of(
          DATA_ACCESS,
          OWN_ASSET_MANAGEMENT,
          ORG_USER_MANAGEMENT,
          ORG_ASSET_MANAGEMENT,
          ORG_ASSET_PUBLISH,
          ORG_PUBLISHER_MANAGEMENT,
          DEPT_USER_MANAGEMENT,
          DEPT_ASSET_MANAGEMENT,
          DEPT_ASSET_PUBLISH,
          DEPT_PUBLISHER_MANAGEMENT,
          ORG_MANAGEMENT,
          ASSET_PUBLISH,
          ASSET_MANAGEMENT,
          USER_MANAGEMENT,
          PUBLISHER_MANAGEMENT,
          ROLE_MANAGEMENT,
          COMPUTE_MANAGEMENT,
          CREDIT_MANAGEMENT);
}