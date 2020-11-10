
import {Tunnel} from './tunnel'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_tunnel_type",'tunnel')
export class TunnelType extends BaseEntity{
	@Id()
	@Column({
		name:'tunnel_type_id',
		type:'int',
		nullable:false
	})
	tunnelTypeId:number;

	@Column({
		name:'tunnel_type_name',
		type:'string',
		nullable:true
	})
	tunnelTypeName:string;

	@Column({
		name:'is_twoway',
		type:'int',
		nullable:true
	})
	isTwoway:number;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	remarks:string;

	@OneToMany({entity:'Tunnel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'tunnelType',lazyFetch:true})
	tunnels:Array<Tunnel>;

}