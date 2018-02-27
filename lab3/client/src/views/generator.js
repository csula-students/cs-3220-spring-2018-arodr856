export default function (store) {
    return class GeneratorComponent extends window.HTMLElement {
        constructor () {
            super();
            this.store = store;
            // TODO: render generator initial view

            // TODO: subscribe to store on change event
            this.onStateChange = this.handleStateChange.bind(this);
            this.store.subscribe(this.onStateChange);
            // TODO: add click event

            this.querySelector(".resource-button").addEventListener('click', () =>{
                this.store.dispatch({type: 'BUY_GENERATOR', payload: 1, generator_id: this.dataset.id});
            });
        }


        handleStateChange(newState) {
            if(this.dataset.id == '0'){
                this.querySelector('.count-label').textContent = newState.gen_0_quantity;
            }
            else if(this.dataset.id == '1'){
                this.querySelector('.count-label').textContent = newState.gen_1_quantity;
            }
            else if(this.dataset.id == '2'){
                this.querySelector('.count-label').textContent = newState.gen_2_quantity;
            }

        }

    };
}
