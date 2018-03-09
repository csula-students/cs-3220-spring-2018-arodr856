export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			// TODO: initial DOM rendering of story itself
			this.innerHTML = this.render();

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			// TODO: display story based on the state "resource" and "stories"
		}

		connectedCallback () {
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}


		render(){
			return ` <p>Nam, maecenas. Ligula pulvinar diam, metus venenatis curae; elit egestas interdum pretium nisl etiam. Praesent
			in mus. Eleifend etiam fusce gravida ac. Sollicitudin iaculis curabitur sapien phasellus. Dictumst eros.
			Hac ornare augue curabitur. Pede tincidunt. Placerat risus laoreet proin lectus primis commodo In sollicitudin
			congue interdum cum rutrum felis, gravida congue fringilla. Porta sit ante ullamcorper tempus nam pulvinar
			tincidunt vivamus euismod fusce pharetra. Ullamcorper augue aptent pretium cum.</p>

			<p>Eu litora nascetur cum duis scelerisque. Potenti habitasse imperdiet ornare congue, orci orci metus dictum.
			Per tincidunt fermentum orci, lorem urna felis metus pulvinar porta egestas nec viverra nisl elit augue
			nascetur elementum. Pellentesque dictum mollis elit facilisi. Libero penatibus, venenatis. Justo, tristique
			porttitor auctor sed leo nulla, class metus penatibus. Molestie scelerisque nec amet gravida. Congue
			mollis mi ultrices eu. Aptent integer mollis.</p>
			<p>Nam, maecenas. Ligula pulvinar diam, metus venenatis curae; elit egestas interdum pretium nisl etiam. Praesent
			in mus. Eleifend etiam fusce gravida ac. Sollicitudin iaculis curabitur sapien phasellus. Dictumst eros.
			Hac ornare augue curabitur. Pede tincidunt. Placerat risus laoreet proin lectus primis commodo In sollicitudin
			congue interdum cum rutrum felis, gravida congue fringilla. Porta sit ante ullamcorper tempus nam pulvinar
			tincidunt vivamus euismod fusce pharetra. Ullamcorper augue aptent pretium cum.</p>

			<p>Eu litora nascetur cum duis scelerisque. Potenti habitasse imperdiet ornare congue, orci orci metus dictum.
			Per tincidunt fermentum orci, lorem urna felis metus pulvinar porta egestas nec viverra nisl elit augue
			nascetur elementum. Pellentesque dictum mollis elit facilisi. Libero penatibus, venenatis. Justo, tristique
			porttitor auctor sed leo nulla, class metus penatibus. Molestie scelerisque nec amet gravida. Congue
			mollis mi ultrices eu. Aptent integer mollis.</p>`
			
		}
	};
}

