    import Generator from "./models/generator";

    export default function reducer(state, action) {
        switch (action.type) {
            case 'EXAMPLE_MUTATION':
            state.example = action.payload;
            return state;
            case 'BUY_GENERATOR':
            state.generators.forEach(generator => {
                if(generator.name === action.payload.name){
                 let genModel = new Generator(generator);
                 let generatorCost = genModel.getCost(); 
                  if(generatorCost <= state.counter){ 
                    state.counter -= generatorCost; 
                    generator.quantity++;
                    generator.unlockValue = genModel.getCost();
                    }
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