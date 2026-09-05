package tfs.evente_set.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfs.evente_set.domain.CategoriaEnum;
import tfs.evente_set.domain.ItemCardapio;
import tfs.evente_set.dto.ItemCardapioDTO;
import tfs.evente_set.repository.ItemCardapioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;

    @Transactional
    public ItemCardapioDTO criarItem(ItemCardapioDTO dto) {
        ItemCardapio item = new ItemCardapio();
        item.setNome(dto.nome());
        item.setDescricao(dto.descricao());
        item.setCategoria(dto.categoria());
        item.setAlergenos(dto.alergenos());
        
        ItemCardapio salvo = itemCardapioRepository.save(item);
        return new ItemCardapioDTO(salvo.getId(), salvo.getNome(), salvo.getDescricao(), salvo.getCategoria(), salvo.getAlergenos());
    }
    
    @Transactional
    public ItemCardapioDTO atualizarItem(Long id, ItemCardapioDTO dto) {
        ItemCardapio item = itemCardapioRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
        item.setNome(dto.nome());
        item.setDescricao(dto.descricao());
        item.setCategoria(dto.categoria());
        item.setAlergenos(dto.alergenos());
        ItemCardapio salvo = itemCardapioRepository.save(item);
        return new ItemCardapioDTO(salvo.getId(), salvo.getNome(), salvo.getDescricao(), salvo.getCategoria(), salvo.getAlergenos());
    }
    
    @Transactional
    public void deletarItem(Long id) {
        itemCardapioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ItemCardapioDTO> listarTodos() {
        return itemCardapioRepository.findAll().stream()
                .map(i -> new ItemCardapioDTO(i.getId(), i.getNome(), i.getDescricao(), i.getCategoria(), i.getAlergenos()))
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<ItemCardapioDTO> listarPorCategoria(CategoriaEnum categoria) {
        return itemCardapioRepository.findByCategoria(categoria).stream()
                .map(i -> new ItemCardapioDTO(i.getId(), i.getNome(), i.getDescricao(), i.getCategoria(), i.getAlergenos()))
                .collect(Collectors.toList());
    }
}
