    import Generator from "./models/generator";

    export default function reducer(state, action) {
        switch (action.type) {
            case 'EXAMPLE_MUTATION':
            state.example = action.payload;
            return state;
            case 'BUY_GENERATOR':
            state.generators.forEach(generator => {
                if(generator.name === action.payload.name){
                    let gen = new Generator(generator);
                    state.counter -= gen.getCost();
                    generator.quantity++;
                    generator.unlockValue = gen.getCost();
                    return state;
                }
            });
            return state;
            case 'BUTTON_CLICK':
            state.counter++;
            return state;
            default:
            return state;
        }
    }