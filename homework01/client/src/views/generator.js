import constants from "../constants";
import generator from "../models/generator";
export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.onStateChange = this.handleStateChange.bind(this);

			
			this.innerHTML = this.render();
			
			this.querySelector(".resource-button").addEventListener('click', () => {
                this.store.dispatch({
                    type: constants.actions.BUY_GENERATOR,
                    payload: {
                        quantity: 1,
                        name: this.dataset.name
                    }
                });
            });

		}

		handleStateChange (newState) {
			 var gens = newState.generators;

            gens.forEach(element => {
               if(element.name === this.dataset.name){
                this.querySelector('.count-label').textContent = element.quantity;
                this.querySelector('.resource-button').textContent = element.unlockValue;
                this.querySelector('.rate').textContent = ((element.quantity * element.rate) * 60) + " per minute";
               
            }
        });

		}

		connectedCallback () {
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}

		render(){
            return `<div class="count-container">
                        <label class="generator-name">
                            ${this.dataset.name}
                        </label>
                        <label class="count-label">0</label>
                    </div>
                    <label class="generator-description">
                        description
                    </label>
                    <en class="resource-button-container">  
                        <label class="rate"></label>
                        <button class="resource-button">${this.store.state.generators[this.dataset.id].baseCost}</button>
                    </en>`
        }

	};
}
