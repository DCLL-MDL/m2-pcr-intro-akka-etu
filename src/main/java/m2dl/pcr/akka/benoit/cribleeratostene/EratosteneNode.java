package m2dl.pcr.akka.benoit.cribleeratostene;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.event.slf4j.Logger;
import m2dl.pcr.akka.benoit.exercice12.HelloGoodbyeActor;

/**
 * Created by benoit on 26/05/2016.
 */
public class EratosteneNode extends UntypedActor {

    private final int primeNumber;

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef nextNode;

    public EratosteneNode(int primeNumber) {
        this.primeNumber = primeNumber;
        log.info("EratosteneNode constructor with number=[{}]", primeNumber);
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg instanceof Integer) {
            int number = (Integer) msg;
            if (number % primeNumber == 0) {

            } else {
                if (nextNode == null) {
                    nextNode = getContext().actorOf(Props.create(EratosteneNode.class, number), "eratostene-node-" + number);
                }
                nextNode.tell(number, getSelf());
            }

        } else {
            unhandled(msg);
        }
    }
}
