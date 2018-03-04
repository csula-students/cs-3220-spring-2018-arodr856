export default function (store) {
    return class GeneratorComponent extends window.HTMLElement {
        constructor() {
            super();
            this.store = store;
            // TODO: render generator initial view
            // TODO: subscribe to store on change event
            this.onStateChange = this.handleStateChange.bind(this);
            this.store.subscribe(this.onStateChange);
            // TODO: add click event
            this.querySelector(".resource-button").addEventListener('click', () => {
                this.store.dispatch({
                    type: 'BUY_GENERATOR',
                    payload: {
                        quantity: 1,
                        name: this.dataset.name
                    }
                });
            });
        }


        handleStateChange(newState) {
            var gens = newState.generators;
            gens.forEach(element => {
               if(element.name === this.dataset.name){
                this.querySelector('.count-label').textContent = element.quantity;
                this.querySelector('.resource-button').textContent = element.unlockValue;
            }
        });
        }
    };
}