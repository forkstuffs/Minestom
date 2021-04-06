package net.minestom.server.network.packet.client.play;

import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.network.packet.client.ClientPlayPacket;
import net.minestom.server.utils.BlockPosition;
import net.minestom.server.utils.binary.BinaryReader;
import net.minestom.server.utils.binary.BinaryWriter;
import org.jetbrains.annotations.NotNull;

public class ClientPlayerDiggingPacket extends ClientPlayPacket {

    public Status status = Status.SWAP_ITEM_HAND;
    public BlockPosition blockPosition = new BlockPosition(0,0,0);
    public BlockFace blockFace = BlockFace.TOP;

    @Override
    public void read(@NotNull BinaryReader reader) {
        this.status = Status.values()[reader.readVarInt()];
        this.blockPosition = reader.readBlockPosition();
        this.blockFace = BlockFace.values()[reader.readByte()];
    }

    @Override
    public void write(@NotNull BinaryWriter writer) {
        writer.writeVarInt(status.ordinal());
        writer.writeBlockPosition(blockPosition);
        writer.writeByte((byte) blockFace.ordinal());
    }

    @Override
    public int getId() {
        return 0x1B;
    }

    public enum Status {
        STARTED_DIGGING,
        CANCELLED_DIGGING,
        FINISHED_DIGGING,
        DROP_ITEM_STACK,
        DROP_ITEM,
        UPDATE_ITEM_STATE,
        SWAP_ITEM_HAND
    }

}
