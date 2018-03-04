    import Generator from "./models/generator";

    export default function reducer(state, action) {
        switch (action.type) {
            case 'EXAMPLE_MUTATION':
                state.example = action.payload;
                    return state;
            case 'BUY_GENERATOR':
                for (let i = 0; i < state.generators.length; i++) {
                    let generator = state.generators[i];
                    if (generator.name === action.payload.name) { 
                        let gen = new Generator(generator);
                        state.counter -= gen.getCost();
                        generator.quantity++;
                        generator.unlockValue = gen.getCost();
                        return state;
                }
            }
            return state;

            default:
            return state;
        }
    }