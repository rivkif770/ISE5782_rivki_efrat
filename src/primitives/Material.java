package primitives;

public class Material {
    public Double3 kD = new Double3(0,0,0), kS = new Double3(0,0,0);
    public int nShininess = 0;

    //region Getters
    /**
     * geting of nShininess
     * @return nShininess
     */
    public int getnShininess() {return nShininess;}
    /**
     * geting of kD
     * @return kD
     */
    public Double3 getkD() {return kD;}
    /**
     * geting of kS
     * @return kS
     */
    public Double3 getkS() {return kS;}
    //endregion

    //region Setters
    // ** all setters implements the Builder Design Pattern **//
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setkD(Double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    public Material setkS(Double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
    //endregion
}
