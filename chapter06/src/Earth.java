public class Earth {
  static final double EARTH_REDIUS = 6400;
  static final double EARTH_SURFACE_AREA;

  static {
    EARTH_SURFACE_AREA = 4 * Math.PI * EARTH_REDIUS * EARTH_REDIUS;
  }
}
