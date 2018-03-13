import constants from "../constants";
export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
		}
		connectedCallback () {
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: constants.actions.INCREMENT, 
					payload: 1
				});
			});
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}
