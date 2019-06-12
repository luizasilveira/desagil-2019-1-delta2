package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandC;
    private final NandGate nandOut1;
    private final NandGate nandOut2;

    public DeMux() {
        super("DeMux", 2, 2);
        nandA = new NandGate();
        nandB = new NandGate();
        nandC = new NandGate();
        nandOut1 = new NandGate();
        nandOut2 = new NandGate();

        nandB.connect(1, nandA);

        nandOut1.connect(0, nandB);
        nandOut1.connect(1, nandB);

        nandOut2.connect(0, nandC);
        nandOut2.connect(1, nandC);

    }

    @Override
    public boolean read(int outputPin) {
        switch (outputPin) {

            case 0:
                return nandOut1.read();
            case 1:
                return nandOut2.read();
            default:
                throw new IndexOutOfBoundsException(outputPin);
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandB.connect(0, emitter);

                nandC.connect(1, emitter);

                break;

            case 1:
                nandA.connect(0, emitter);

                nandA.connect(1, emitter);

                nandC.connect(0, emitter);
                break;

            default:
                throw new IndexOutOfBoundsException(inputPin);


        }
    }
}
