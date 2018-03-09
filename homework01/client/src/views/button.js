export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
		}

		connectedCallback () {
			this.addEventListener('click', () => {
				this.store.dispatch({type: 'BUTTON_CLICK'});
			});
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}


	};
}
