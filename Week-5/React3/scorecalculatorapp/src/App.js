import React from 'react';
import { CalculateScore } from './components/CalculateScore';

function App() {
  return (
    <div>
      <CalculateScore 
        Name="Aakansha"
        School="GCPS"
        total={250}
        goal={2}
      />
    </div>
  );
}

export default App;
