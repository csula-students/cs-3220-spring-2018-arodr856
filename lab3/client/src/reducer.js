import Generator from "./models/generator";

export default function reducer(state, action) {
    switch (action.type) {
        case 'EXAMPLE_MUTATION':
            state.example = action.payload;
            return state;
        case 'BUY_GENERATOR':
            if (state.counter == null) {
                state.counter = 10;
                let genInfo = {
                    type: 'autonomous',
                    name: action.payload.name,
                    description: 'desc',
                    rate: 10,
                    quantity: 1,
                    baseCost: 10,
                    unlockValue: 10
                };
                let g = new Generator(genInfo);
                state.generators = [];
                state.generators.push(g);
            } else {
                for (let i = 0; i < state.generators.length; i++) {
                    const generator = state.generators[i];
                    if (generator.name === action.payload.name) {
                        let temp = new Generator(generator);
                        let cost = temp.getCost();
                        state.counter -= cost;
                        generator.quantity++;
                       // console.log(state.generators);
                        return state;
                    }
                }
                let gen1 = {
                    type: 'autonomous',
                    name: action.payload.name,
                    description: 'desc',
                    rate: 10,
                    quantity: 1,
                    baseCost: 10,
                    unlockValue: 10
                };
                let g = new Generator(gen1);
               
                state.generators.push(g);
              //  console.log(state.generators);

            }
            return state;
        default:
            return state;
    }
}