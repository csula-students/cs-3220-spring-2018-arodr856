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

            let generatorName = action.payload.name;
            const generatorData = {
                type: 'autonomous',
                name: generatorName,
                description: 'desc',
                quantity: 0,
                baseCost: 0, 
                unlockValue: 0
            }

            if(generatorName === "tree"){
                generatorData.rate = 10;
                generatorData.baseCost = 10;
                generatorData.unlockValue = 10;
            }
            else if(generatorName === "factory"){
                generatorData.rate = 25;
                generatorData.baseCost = 35;
                generatorData.unlockValue = 35;
            }
            else if (generatorName === "rain"){
                generatorData.rate = 45;
                generatorData.baseCost = 70;
                generatorData.unlockValue = 70;
            }
            generatorData.quantity++;
            state.counter -= generatorData.baseCost;
            let g = new Generator(generatorData);
            g.unlockValue = g.getCost();
            state.generators.push(g);
            return state;

            default:
            return state;
        }
    }