function openModal() {
    document.getElementById("modal").style.display = "block";
  }
  
  function closeModal() {
    document.getElementById("modal").style.display = "none";
  }
  
  function updateArticle() {
    const price = document.getElementById("price").value;
    const availability = document.getElementById("availability").value;
    const description = document.getElementById("description").value;
    alert(`Article updated! \nPrice: ${price} \nAvailability: ${availability} \nDescription: ${description}`);
  }
  
  function deleteArticle() {
    const confirmDelete = confirm("Are you sure you want to delete this article?");
    if (confirmDelete) {
      alert("Article deleted!");
      closeModal();
      
    }
  }