class Hexagon {
    double [] points;
    double center;
    public Hexagon(double side){
      
      double C = side;
      double A = (.5)*C;
      double B = Math.sin(60)*70;
      points = new double[12];
      //pointy hexagon
      //     X                          Y
      points[0] = 0.0;           points[1] = A+C;
      points[2] = 0.0;          points[3] = A;
      points[4] = B; 			points[5] = 0.0;
      points[6] = 2*B;          points[7] = A;
      points[8] = 2*B;           points[9] = A+C;
      points[10] = B;      points[11] = 2*C;
      
      //flat hexagon
      /*points[0] = 0.0;           points[1] = 0.0;
      points[2] = side;          points[3] = 0.0;
      points[4] = side+(side/2); points[5] = center;
      points[6] = side;          points[7] = center * 2;
      points[8] = 0.0;           points[9] = center * 2;
      points[10] = -side/2;      points[11] = center;*/
      
    }


    public double [] getPoints(){
      return points;
    }
}