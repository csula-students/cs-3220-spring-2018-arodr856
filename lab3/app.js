window.incrementalGame = {
    state: {
        counter: 0
    }
};

// PubSub is single object for publish data to multiple subscribers
class PubSub {
    constructor() {
        this.subscribers = [];
    }

    // subscribe allows a new subscriber to listen for changes by providing
    // callback function in the parameter
    subscribe(fn) {
        this.subscribers.push(fn);
    }

    // one can publish any data to subscribers
    publish(data) {
        this.subscribers.forEach(subscriber => {
            subscriber(data);
        });
    }
}
let pubSub = new PubSub();

function updateCount(count) {
    let dom = document.querySelector("#count");
    dom.textContent = count;
}
pubSub.subscribe(updateCount);


let dom = document.querySelector("#action_button");
dom.addEventListener('click', function () {
    pubSub.publish(++window.incrementalGame.state.counter);
});