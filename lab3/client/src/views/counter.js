export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			//console.log('CounterComponent#stateChange', this, newState);
			this.querySelector('#count').textContent = newState.counter;
		}

		connectedCallback () {
			this.querySelector('#count').textContent = 0;
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

