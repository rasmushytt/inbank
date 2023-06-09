export default {
  sendPersonalInfoToBackEnd(personalInfo) {
    return fetch('http://localhost:8083/api/inBankApi/approval', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(personalInfo),
      })
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Request failed with status:', response.status);
      }
    })
    .then(data => {
      return data
    })
    .catch((error) => {
      throw error
    });
  }
}
