import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Extension of {@code NaturalNumber2} with secondary operations implemented as
 * instance methods: add, subtract, and power.
 *
 * @author Jacob Ruth
 *
 */
public final class NaturalNumberInstanceOps extends NaturalNumber2 {

    /**
     * Default constructor.
     */
    public NaturalNumberInstanceOps() {
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumberInstanceOps(int i) {
        super(i);
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumberInstanceOps(String s) {
        super(s);
    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumberInstanceOps(NaturalNumber n) {
        super(n);
    }

    /**
     * The base used in representing {@code NaturalNumber}.
     */
    private static final int RADIX = 10;

    @Override
    public void add(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        /**
         * @decreases n
         */
        int thisLowDigit = this.divideBy10();
        int nLowDigit = n.divideBy10();
        if (!n.isZero()) {
            this.add(n);
        }
        thisLowDigit += nLowDigit;
        if (thisLowDigit >= RADIX) {
            thisLowDigit -= RADIX;
            this.increment();
        }
        this.multiplyBy10(thisLowDigit);
        n.multiplyBy10(nLowDigit);
    }

    @Override
    public void subtract(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        assert this.compareTo(n) >= 0 : "Violation of: this >= n";
        int thisLowDigit = this.divideBy10();
        int nLowDigit = n.divideBy10();
        if (!n.isZero()) {
            this.subtract(n);
        }
        thisLowDigit -= nLowDigit;
        if (thisLowDigit < 0) {
            thisLowDigit = RADIX + thisLowDigit;
            this.subtract(new NaturalNumber2(1));
        }
        this.multiplyBy10(thisLowDigit);
        n.multiplyBy10(nLowDigit);
    }

    @Override
    public void power(int p) {
        assert p >= 0 : "Violation of: p >= 0";
        NaturalNumber copyThis = new NaturalNumber2();
        copyThis.copyFrom(this);
        if (p > 0) {
            if (p % 2 == 0) {
                this.power(p / 2);
                copyThis.copyFrom(this);
                this.multiply(copyThis);
            } else {
                this.power(p - 1);
                this.multiply(copyThis);
            }
        } else {
            this.setFromInt(p + 1);
        }
    }
}
