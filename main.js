function createImageSlider(images, elementId, interval = 1500) {
    let currentIndex = 0;
    const imageElement = document.getElementById(elementId);
  
    function changeImage() {
      imageElement.style.backgroundImage = `url('${images[currentIndex]}')`;
      currentIndex = (currentIndex + 1) % images.length;
    }
  
    setInterval(changeImage, interval); // Change l'image à l'intervalle donné
    changeImage(); // Initialiser la première image
  }



  // Tableau d'images pour la première galerie
const images = [
    "https://i.pinimg.com/736x/2f/b4/b7/2fb4b7b89cc8a1ace3fca6b55405d4d3.jpg",
    "https://i.pinimg.com/736x/46/e0/01/46e00132471e39acb3914e77f42c8393.jpg",
    "https://i.pinimg.com/736x/4b/30/3c/4b303c3cb8ea842dcb1b05ff2d80f8fa.jpg",
    "https://i.pinimg.com/736x/f6/ee/25/f6ee259a7b0b3d56c05346e5b25cb0ed.jpg",
    "https://i.pinimg.com/736x/53/68/d0/5368d067b7516c374021a9826df8b11f.jpg",
    "https://i.pinimg.com/736x/4a/50/2c/4a502c6f8b29edd1f9a9da5865dd550b.jpg",
    "https://i.pinimg.com/736x/50/62/f7/5062f772bb7d5831d4e5eeae21304849.jpg",
    "https://i.pinimg.com/736x/0c/a7/a1/0ca7a1a40328e13c89e5323e9afc29f9.jpg"
    
  ];
  
  // Tableau d'images pour la deuxième galerie
  const images2 = [
    "https://i.pinimg.com/736x/2c/1a/4d/2c1a4d027abc77c2777e6af3b765946a.jpg",
    "https://i.pinimg.com/736x/9b/60/82/9b608268cebcaeda6365eeac85109f20.jpg",
    "https://i.pinimg.com/736x/95/64/68/9564689ac730c38a87c6e7641effc2f0.jpg",
    "https://i.pinimg.com/736x/8a/cf/6b/8acf6b6060ee3bfd45dbbc7de8deca0f.jpg",
    "https://i.pinimg.com/736x/d8/b6/8d/d8b68d8cfdc0228ba6ac714c482c1121.jpg"
    
  ]
  
  // Créer la première galerie
  createImageSlider(images, 'dynamic-image', 1500);
  
  // Créer la deuxième galerie
  createImageSlider(images2, 'gallery-image', 2500);


  document.querySelectorAll('.categories-list ul li a').forEach(link => {
    link.addEventListener('click', function(event) {
        // Empêcher le comportement par défaut du lien (pour tester l'animation)
        event.preventDefault();

        // Masquer la flèche et le texte en ajoutant la classe "hide-all"
        this.classList.add('hide-all');

        // Rediriger vers la page après un court délai (pour voir l'animation)
        setTimeout(() => {
            window.location.href = this.getAttribute('href');
        }, 300); // Délai de 300 ms pour laisser l'animation se terminer
    });
});


